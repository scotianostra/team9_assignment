from android.models import *
from android.serializers import StudentSerializer, StaffSerializer, ClassSerializer
from rest_framework import generics
from rest_framework.response import Response
from rest_framework import status
import datetime
from django.utils import timezone

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


class ClassSign(generics.UpdateAPIView):
    serializer_class = ClassSerializer
    
    def put(self, request, format=None):
        roomid = request.data["room_id"]
        studentid = request.data["student_id"]
        
        signFor = None
        
        #Select all classes which start within +/- 60 minutes
        now = timezone.now()
        startTime = now + timezone.timedelta(minutes = -60)
        endTime = now + timezone.timedelta(minutes = 60)
        classes = Class.objects.filter(Q(occurance__range = (startTime, endTime)) & Q(room = roomid))
        
        #Find the classes which are within the 45 minutes sign in window
        for thisClass in classes:
            minutesToStart = (thisClass.occurance - now).total_seconds() % 3600 // 60
            if minutesToStart <= 15 and minutesToStart >= -30:
                for module in Module.objects.filter(Q(students_enrolled__id__exact = studentid) & Q(moduleid = thisClass.moduleid)):
                    print("hello world")
        
        #for thisClass in Class.objects.all():
        #    classStartDiff = (thisClass.occurance.replace(tzinfo=None) - datetime.datetime.now()).days * 24 * 60
        #    classEndDiff = (datetime.datetime.now() - thisClass.occurance.replace(tzinfo=None)).days * 24 * 60
        #    print(classStartDiff)
        #    print(classEndDiff)
        #    if (classStartDiff <= 15 and classStartDiff >= 0) or (classEndDiff >= 0 and classEndDiff <= 30):
        #        signFor = thisClass
        #        
        #        break
        
        student = Student.objects.get(matric_number = studentid)
        
        result = 'success'
        responseStatus = status.HTTP_200_OK
        
        if not signFor:
            result = 'A class with class_id ' + classID + ' does not exist.'
            responseStatus = status.HTTP_404_NOT_FOUND
        elif signFor.class_register.get(matric_number = studentid):
            result = 'The student with student_id ' + studentid + ' has already signed in for the class with class_id ' + classID
            responseStatus = status.HTTP_409_CONFLICT
        elif not student:
            result = 'A student with student_id ' + studentid + ' does not exist.'
            responseStatus = status.HTTP_404_NOT_FOUND
        else:
            signFor.class_register.add(student)
        
        content = {
            'result': result
        }
        
        return Response(content, status = responseStatus)