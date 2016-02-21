from rest_framework import serializers
from android.models import *


class StudentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Student
        fields = ('matric_number', 'email', 'first_name', 'last_name')


class StaffSerializer(serializers.ModelSerializer):
    class Meta:
        model = Staff
        fields = ('staffid', 'email', 'first_name', 'last_name')


class StaffModuleListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Module
        fields = ('module_code', 'module_title', 'coordinators')


class StaffLoginSerializer(serializers.ModelSerializer):
    class Meta:
        model = Staff
        fields = ('hash', 'staffid')


class StudentLoginSerializer(serializers.ModelSerializer):
    class Meta:
        model = Student
        fields = ('hash', 'matric_number')