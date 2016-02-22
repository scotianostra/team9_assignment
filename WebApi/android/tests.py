import unittest
#from unittest.mock import Mock, patch

from django.http import HttpRequest
from django.test import TestCase
<<<<<<< HEAD
from android.views import login, staff_module_list
from django.utils.html import escape
=======
from android.views import login
from rest_framework.request import Request
>>>>>>> 87ac7f83ecf9d8693060a877fa2483726d0a4235


class LoginTest(TestCase):

    def test_cant_login_if_not_registered(self):
        request = Request()
        request.method = 'POST'
        request.POST['email_address'] = 'ouanixi@gmail.com'
        request.POST['password'] = 'wrong_password'
        response = login(request)

        self.assertEqual(response.status_code, 204)

<<<<<<< HEAD

    # def test_return_404_if_staffid_not_found(self):
    #     request = HttpRequest()
    #     request.method = 'GET'
    #     request.POST['staffid'] = '0'
    #     response = staff_module_list(request)
    #
    #     self.assertEqual(response.status_code, 404)

=======
>>>>>>> 87ac7f83ecf9d8693060a877fa2483726d0a4235
    # def test_staff_can_login_if_registered(self):
    #     request = HttpRequest()
    #     request.method = 'POST'
    #     request.POST['email_address'] = 'ouanixi@gmail.com'
    #     request.POST['password'] = 'password'
    #     response = login(request)
    #
    #     self.assertTrue(type(response.data) == 'JSON' )
<<<<<<< HEAD

    # def test_staff_can_login_if_registered(self):
    #     request = HttpRequest()
    #     request.method = 'POST'
    #     request.POST['email_address'] = 'ouanixi@gmail.com'
    #     request.POST['password'] = 'password'
    #     response = login(request)
    #     self.assertTrue(type(response.data) == 'JSON' )
=======
>>>>>>> 87ac7f83ecf9d8693060a877fa2483726d0a4235


