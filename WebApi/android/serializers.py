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


class StaffModuleListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Module
        fields = ('module_code', 'module_title', 'coordinators')


class ModuleSerializer(serializers.ModelSerializer):
    class Meta:
        model = Module
        fields = ('hash', 'staffid')
