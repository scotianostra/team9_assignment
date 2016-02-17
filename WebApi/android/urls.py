from django.conf.urls import url
from android import views

urlpatterns = [
    url(r'^students/$', views.student_list),
    #url(r'^students/(?P<pk>[0-9]+)/$', views.snippet_detail),
]