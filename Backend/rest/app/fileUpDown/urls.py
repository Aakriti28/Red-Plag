from django.conf.urls import url
from rest.app.user.views import UserRegistrationView
from rest.app.user.views import UserLoginView
from rest.app.user.views import HelloView
from rest.app.fileUpDown.views import UploadView
from rest.app.fileUpDown.views import FilesOfUserView
from rest.app.fileUpDown.views import CompareNDownload
from rest.app.fileUpDown.views import DownloadFile
from rest.app.fileUpDown.views import DownloadResultImage
from rest.app.fileUpDown.views import DeleteView

urlpatterns = [
    url(r'^upload/$', UploadView.as_view()),
    url(r'^lists/$', FilesOfUserView.as_view()),
    url(r'^compare/$', CompareNDownload.as_view()),
    url(r'^download/$', DownloadFile.as_view()),
    url(r'^resim/$', DownloadResultImage.as_view()),
    url(r'^delete/$', DeleteView.as_view()),

    ]
