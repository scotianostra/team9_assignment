from android.models import *
from android.serializers import StudentSerializer, StaffSerializer, ClassSerializer
from rest_framework import generics
from rest_framework.response import Response
from rest_framework import status

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
        classID = request.data["class_id"]
        studentID = request.data["class_id"]
        
        signFor = Class.objects.get(classid = classID)
        student = Student.objects.get(matric_number = studentID)
        
        result = 'success'
        responseStatus = status.HTTP_200_OK
        
        if not signFor:
            result = 'A class with class_id ' + classID + ' does not exist.'
            responseStatus = status.HTTP_404_NOT_FOUND
        elif signFor.class_register.get(matric_number = studentID):
            result = 'The student with student_id ' + studentID + ' has already signed in for the class with class_id ' + classID
            responseStatus = status.HTTP_409_CONFLICT
        elif not student:
            result = 'A student with student_id ' + studentID + ' does not exist.'
            responseStatus = status.HTTP_404_NOT_FOUND
        else:
            signFor.class_register.add(student)
        
        content = {
            'result': result
        }
        
        return Response(content, status = responseStatus)