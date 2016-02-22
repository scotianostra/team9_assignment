import unittest
#from unittest.mock import Mock, patch

from django.http import HttpRequest
from django.test import TestCase
from android.views import login, staff_module_list
from django.utils.html import escape


class LoginTest(TestCase):

    def test_cant_login_if_not_registered(self):
        request = HttpRequest()
        request.method = 'POST'
        request.POST['email_address'] = 'ouanixi@gmail.com'
        request.POST['password'] = 'wrong_password'
        response = login(request)

        self.assertEqual(response.status_code, 204)


    # def test_return_404_if_staffid_not_found(self):
    #     request = HttpRequest()
    #     request.method = 'GET'
    #     request.POST['staffid'] = '0'
    #     response = staff_module_list(request)
    #
    #     self.assertEqual(response.status_code, 404)

    # def test_staff_can_login_if_registered(self):
    #     request = HttpRequest()
    #     request.method = 'POST'
    #     request.POST['email_address'] = 'ouanixi@gmail.com'
    #     request.POST['password'] = 'password'
    #     response = login(request)
    #
    #     self.assertTrue(type(response.data) == 'JSON' )

    # def test_staff_can_login_if_registered(self):
    #     request = HttpRequest()
    #     request.method = 'POST'
    #     request.POST['email_address'] = 'ouanixi@gmail.com'
    #     request.POST['password'] = 'password'
    #     response = login(request)
    #     self.assertTrue(type(response.data) == 'JSON' )


