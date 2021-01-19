
from django.conf.urls import url
from rest.app.user.views import UserRegistrationView
from rest.app.user.views import UserLoginView
from rest.app.user.views import HelloView
from rest.app.user.views import ChangePasswordView


urlpatterns = [
    url(r'^signup', UserRegistrationView.as_view()),
    url(r'^signin', UserLoginView.as_view()),
          url(r'^hello/$', HelloView.as_view()),
           url(r'^change/$', ChangePasswordView.as_view()),

    ]
