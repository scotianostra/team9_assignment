from rest_framework import serializers
from android.models import *


class StudentSerializer(serializers.ModelSerializer):

    class Meta:
        model = Student
        fields = ('matric_number', 'email', 'firstname', 'lastname')


class StaffSerializer(serializers.ModelSerializer):

    class Meta:
        model = Staff
        fields = ('staff_id', 'email', 'firstname', 'lastname')