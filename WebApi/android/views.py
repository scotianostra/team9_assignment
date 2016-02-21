from android.models import *
from android.serializers import StudentSerializer, StaffSerializer, ClassSerializer, ClassRegisterSerializer
from rest_framework import generics
from rest_framework.response import Response
from rest_framework import status
import json
from django.utils import timezone
from django.db.models.query import Q
from itertools import chain

class StudentList(generics.ListCreateAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer


class StudentDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer


class StaffList(generics.ListCreateAPIView):
    queryset = Staff.objects.all()
    serializer_class = StaffSerializer


class StaffDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Staff.objects.all()
    serializer_class = StaffSerializer

class ClassRegister(generics.ListCreateAPIView):
    serializer_class = StudentSerializer
    def get(self, request, format=None):
        searchid = request.data["class_id"]
        
        classToCheck = Class.objects.filter(classid = searchid)[:1].get()
        signedIn = classToCheck.class_register.all()
        studentsOnModule = Module.objects.filter(moduleid = classToCheck.moduleid_id)[:1].get().students_enrolled.all()
        
        registerList = list(chain(signedIn, studentsOnModule))
        registerList = sorted(registerList, key = lambda instance: instance.last_name)
        seen = []
        resultList = []
        
        for student in registerList:
            found = False
            for seenStudent in seen:
                if student.matric_number == seenStudent:
                    found = True
                    break
            
            if found == False:
                seen.append(student.matric_number)
                hasSigned = False
                for signedStudent in signedIn:
                    if signedStudent.matric_number == student.matric_number:
                        hasSigned = True
                
                student.has_signed = hasSigned;
                resultList.append(student)
        
        #result = json.dumps([o.dump() for o in resultList])
        #content = {
        #    'result': result
        #}
        
        serializer = ClassRegisterSerializer(resultList, many=True)
        return Response(serializer.data)
        
        responseStatus = status.HTTP_200_OK
        return Response(content, status = responseStatus)


class ClassSign(generics.UpdateAPIView):
    serializer_class = ClassSerializer
    
    def put(self, request, format=None):
        roomid = request.data["room_id"]
        studentid = request.data["student_id"]
        
        #Select all classes which start within +/- 60 minutes in the specified room
        now = timezone.now()
        startTime = now + timezone.timedelta(minutes = -60)
        endTime = now + timezone.timedelta(minutes = 60)
        classes = Class.objects.filter(Q(occurance__range = (startTime, endTime)) & Q(room = roomid))
        
        result = 'success'
        responseStatus = status.HTTP_200_OK
        
        signedIn = False
        action = 0
        
        #Find the classes which are within the 45 minutes sign in window that the student is enrolled in and sign the student into them
        for thisClass in classes:
            minutesToStart = (thisClass.occurance - now).total_seconds() % 3600 // 60
            minutesToStart = minutesToStart - 60
            if minutesToStart <= 15 and minutesToStart >= -30:
                action = 1
                if Module.objects.filter(Q(students_enrolled__exact = studentid) & Q(moduleid = thisClass.moduleid_id)).count() != 0:
                    if thisClass.class_register.get(matric_number = studentid):
                        action = 2
                    else:
                        thisClass.class_register.add(student)
                        signedIn = True
        
        if signedIn == True:
            result = 'Student id ' + str(studentid) + ' has been signed into all classes with an open register in room id ' + str(roomid)
        else:
            if action == 0:
                result = 'There are no classes with an open register taking place in room id ' + str(roomid) + ' at this time.'
                responseStatus = status.HTTP_404_NOT_FOUND
            elif action == 1:
                result = 'There is a class taking place in ' + str(roomid) + ' but student id ' + str(studentid) + ' is not enrolled in this class.'
                responseStatus = status.HTTP_404_NOT_FOUND
            elif action == 2:
                result = 'Student id ' + str(studentid) + ' is already signed into all classes currently available in room id ' + str(roomid)
                responseStatus = status.HTTP_409_CONFLICT
        
        content = {
            'result': result
        }
        
        return Response(content, status = responseStatus)