from rest_framework.test import APITestCase
from rest_framework import status
from android.models import Staff, Student, Class, Module
from datetime import datetime, timedelta



class LoginTest(APITestCase):

    def setUp(self):
        now = datetime.now()
        later = now + timedelta(hours=9)

        # Create a couple of staff instances
        staff1 = Staff(staffid=101, email= 'staff01@test.com', first_name='John', last_name='Doe',
                  password='password', hash= 'staff')
        staff2 = Staff(staffid=102, email= 'staff02@test.com', first_name='Dave', last_name='Doe',
                  password='password', hash= 'staff')
        staff1.save()
        staff2.save()

        # Create a couple of student instances
        student1 = Student(matric_number=201, email= 'student01@test.com', first_name='John', last_name='Coltrane',
                  password='password', hash= 'student')
        student2 = Student(matric_number=202, email= 'student02@test.com', first_name='Dave', last_name='Coltrane',
                  password='password', hash= 'student')
        student1.save()
        student2.save()

        # Create a couple of modules
        module1 = Module(moduleid=6985, module_code='AC0021', module_title='Programming With Java')
        module2 = Module(moduleid=6987, module_code='AC0031', module_title='Programming With Python')
        module1.save()
        module2.save()
        # Adding students and lecturers to module1
        module1.coordinators.add(staff1)
        module1.coordinators.add(staff2)
        module1.students_enrolled.add(student1)
        module1.students_enrolled.add(student2)

        module1.save()

        # Create a class
        cls = Class(room_id='qmbsmr', start_time= now, qrCode=120, end_time=later, module=module1)
        cls.save()
        cls.class_register.add(student2)
        cls.save()

    def test_cant_login_if_not_registered(self):
        url = '/login/'
        data = {'email_address': 'doesntexist', 'password': 'wrong'}
        response = self.client.post(url, data)
        self.assertEqual(response.status_code, status.HTTP_406_NOT_ACCEPTABLE)

    def test_staff_can_login_if_registered(self):
        url = '/login/'
        data = {'email_address': 'staff01@test.com', 'password': 'password'}
        response = self.client.post(url, data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.data, {'staffid': 101, 'hash': 'staff'})

    def test_studet_can_login_if_registered(self):
        url = '/login/'
        data = {'email_address': 'student01@test.com', 'password': 'password'}
        response = self.client.post(url, data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.data, {'matric_number': 201, 'hash': 'student'})

    def test_list_all_students(self):
        url = '/students/'
        response = self.client.get(url)
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(len(response.data), 2)

    def test_list_all_staff(self):
        url = '/staff/'
        response = self.client.get(url)
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(len(response.data), 2)

    def test_list_all_modules(self):
        url = '/modules/'
        response = self.client.get(url)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_staff_module_list(self):
        url = '/staffModuleList/101'
        response = self.client.get(url)

        self.assertEquals(response.status_code, status.HTTP_200_OK)
        self.assertEquals(len(response.data), 1)