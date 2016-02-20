from django.test import TestCase
from android.models import *
from django.utils import timezone

class TestClassSign(TestCase):
    def setUp(self):
        student = Student.objects.create(email="test@test.com", matric_number=1, first_name="John", last_name="Doe", password="1234test");
        student.save()
        module = Module.objects.create(moduleid=1, module_code="AC30000", module_title="Agile Software Engineering")
        module.save()
        module.students_enrolled.add(student)
        testClass = Class.objects.create(classid=1, qrCode=1, occurance=timezone.now(), room="1", building="QMB")
        testClass.save()
        testClass.moduleid = 1
    
    def test_noClassAvailable(self):
        result = self.client.put('/students/sign/', '{"student_id": 1, "room_id": 1}', 'application/json')
        self.assertEqual(result.status_code, 404)
    
    #def test_studentUnableToSignInOutsideSignInWindow(self):
        #result = self.client.put('/students/sign/', '{"student_id": 1, "room_id": 1}', 'application/json')
        #self.assertEqual(result.status_code, 404)

#Todo: Write remaining tests.