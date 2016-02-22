import unittest
from unittest.mock import Mock, patch

from django.http import HttpRequest
from django.test import TestCase
from android.views import login
from rest_framework.request import Request


class LoginTest(TestCase):

    def test_cant_login_if_not_registered(self):
        request = Request()
        request.method = 'POST'
        request.POST['email_address'] = 'ouanixi@gmail.com'
        request.POST['password'] = 'wrong_password'
        response = login(request)

        self.assertEqual(response.status_code, 204)

    # def test_staff_can_login_if_registered(self):
    #     request = HttpRequest()
    #     request.method = 'POST'
    #     request.POST['email_address'] = 'ouanixi@gmail.com'
    #     request.POST['password'] = 'password'
    #     response = login(request)
    #
    #     self.assertTrue(type(response.data) == 'JSON' )


