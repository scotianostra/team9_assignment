from rest_framework import serializers
from android.models import *


class StudentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Student
        fields = ('matric_number', 'email', 'first_name', 'last_name')


class StaffSerializer(serializers.ModelSerializer):
    class Meta:
        model = Staff
        fields = ('staff_id', 'email', 'first_name', 'last_name')


class ModuleSerializer(serializers.ModelSerializer):
    class Meta:
        model = Module
#        fields = ('module_id','module_code','module_title')


class StudentEnrolledSerializer(serializers.ModelSerializer):
    students = StudentSerializer (many=True, read_only=True)

    class Meta:
        model = Module
