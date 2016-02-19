from android.models import *
from android.serializers import *
from rest_framework import generics
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response


@api_view(['GET', 'PUT', 'DELETE'])
def staff_module_list(request, pk):

    try:
        modules = Module.objects.filter(coordinators=pk)
    except Module.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = StaffModuleListSerializer(modules, many=True)
        return Response(serializer.data)

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


@api_view(['GET', 'POST'])
def login(request):
    if request.method == 'POST':
        email = request.POST['email_address']
        password = request.POST['password']
        try:
            staff = Staff.objects.get(email=email)
            if staff.password == password:
                return Response({'id': staff.staffid, 'hash': staff.hash})
        except(KeyError, Staff.DoesNotExist):
            try:
                student = Student.objects.get(email=email)
                if student.password == password:
                    return Response({'id': student.staffid, 'hash': student.hash})
            except(KeyError, Student.DoesNotExist):
                return Response(status=status.HTTP_204_NO_CONTENT)
    return Response(status=status.HTTP_400_BAD_REQUEST)

