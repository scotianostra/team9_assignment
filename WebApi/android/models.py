from django.db import models

# Create your models here.


class Staff(models.Model):
    staffid = models.IntegerField(primary_key=True)
    email = models.CharField(max_length=60)
    first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=30)
    password = models.CharField(max_length=30)


class Student(models.Model):
    email = models.CharField(max_length=60)
    matric_number = models.IntegerField(primary_key=True)
    first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=30)
    password = models.CharField(max_length=30)


class Module(models.Model):
    moduleid = models.IntegerField(primary_key=True)
    module_code = models.CharField(max_length=20)
    module_title = models.CharField(max_length=50)
    coordinators = models.ManyToManyField(Staff)
    students_enrolled = models.ManyToManyField(Student)


class Class(models.Model):
    classid = models.IntegerField(primary_key=True)
    qrCode = models.IntegerField()
    occurance = models.DateTimeField()
    room = models.CharField(max_length=10)
    building = models.CharField(max_length=20)
    class_register = models.ManyToManyField(Student)

