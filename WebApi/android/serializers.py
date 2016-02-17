from rest_framework import serializers
from android.models import Student


class StudentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Student
        fields = ('matric_number',)

