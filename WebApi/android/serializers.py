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


class ClassSerializer(serializers.ModelSerializer):
    class Meta:
        model = Class
        fields = ('classid', 'qrCode', 'occurance', 'room', 'building')
		
class ClassRegisterSerializer(serializers.ModelSerializer):
    class Meta:
        model = Student
        fields = ('matric_number', 'email', 'first_name', 'last_name', 'has_signed')