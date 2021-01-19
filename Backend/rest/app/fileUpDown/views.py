from django.shortcuts import render
from rest_framework import status , viewsets
from rest_framework.generics import CreateAPIView, RetrieveAPIView
from rest_framework.views import APIView
from rest_framework.exceptions import ParseError
from rest_framework.parsers import FileUploadParser
from rest_framework.parsers import MultiPartParser, FormParser
from rest_framework.response import Response
from rest_framework.permissions import AllowAny, IsAuthenticated
from .models import File
from rest.app.fileUpDown.serializers import FileSerializer
from django.http import HttpResponse
from django.http import JsonResponse
from django.conf import settings
import mimetypes

from os import listdir, remove
from os.path import isfile, join, basename, exists , isdir
from rest.checker_core.Final_Checker import *

# Create your views here.


class UploadView(APIView):
    serializer_class=FileSerializer
    permission_classes = (IsAuthenticated,)

    def post(self, request, *args, **kwargs):
        custom_data={}
        custom_data['file']=request.data['file']
        custom_data['userid']=request.user.id
        print(custom_data['userid'])
        file_serializer = self.serializer_class(data=custom_data)

        if file_serializer.is_valid():
            file_serializer.save()
            filename_temp=basename(file_serializer.data['file'])
            #print(filename_temp)
            dir_pathh='/'.join([settings.MEDIA_ROOT,str(custom_data['userid']), filename_temp])
            #print(dir_pathh)
            #dir_path_t2=basename()
            #RunCheck(dir_pathh)
            return Response(file_serializer.data, status=status.HTTP_201_CREATED)
        else:
            return Response(file_serializer.errors, status=status.HTTP_400_BAD_REQUEST)



class DeleteView(APIView):
    permission_classes = (IsAuthenticated,)

    def post(self, request, *args, **kwargs):
        custom_data={}
        custom_data['file']=str(request.data['filename'])
        custom_data['userid']=request.user.id
        print(custom_data['userid'])

        user_files = File.objects.all().filter(userid=custom_data['userid'])
        print(user_files)
        #user_files.delete()

        try:
            File.objects.get(userid=custom_data['userid'],file='/'.join([str(custom_data['userid']), custom_data['file']])).delete()
            return Response(status=status.HTTP_200_OK)
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)








class CompareNDownload(APIView):
    permission_classes = (IsAuthenticated,)
    def post(self, request):
        #print(my_view_id(request))
        useridd=request.user.id
        filename=request.data.get('filename')
        boilname=request.data.get('boilname')
        print(filename)
        dir_path= '/'.join([settings.MEDIA_ROOT,str(useridd)])
        file_path= join(dir_path, filename)
        if boilname.endswith(('.cpp','.py', '.java')):
            boil_path= join(dir_path, boilname)

        formats=(".tar", ".tar.gz", ".zip")


        if exists(file_path) and file_path.endswith(formats):
            if boilname.endswith(('.cpp','.py', '.java')):
                boil_path= join(dir_path, boilname)
                if exists(boil_path):
                    out_stat , out_dir = RunCheck(file_path, boil_path)
                else:
                    out_stat , out_dir = RunCheck(file_path)
            else:
                out_stat , out_dir = RunCheck(file_path)

            if out_stat=='success':
                res_path=join(out_dir, 'Results.zip')
                zipf = zipfile.ZipFile(res_path, 'w', zipfile.ZIP_DEFLATED)
                for root, dirs, files in os.walk(out_dir):
                    for file in files:
                        if file.endswith(('.csv', '.png')):
                            zipf.write(os.path.join(root, file), file)
                zipf.close()

                mime_type, _ = mimetypes.guess_type(res_path)
                with open(res_path, 'rb') as fh:
                    response = HttpResponse( fh , content_type=mime_type)
                    response['Content-Disposition'] = "attachment; filename=%s" % basename(res_path)
                    return response
            else:
                print('HI')
                return Response(status=status.HTTP_400_BAD_REQUEST)


        else:
            return Response(status=status.HTTP_400_BAD_REQUEST)



class DownloadFile(APIView):
    permission_classes = (IsAuthenticated,)
    def post(self, request):
        useridd=request.user.id
        filename=request.data.get('filename')
        #print(filename)
        dir_path= '/'.join([settings.MEDIA_ROOT,str(useridd)])
        file_path= join(dir_path, filename)
        mime_type, _ = mimetypes.guess_type(file_path)

        if exists(file_path):
            with open(file_path, 'rb') as fh:
                response = HttpResponse( fh , content_type=mime_type)
                response['Content-Disposition'] = "attachment; filename=%s" % basename(file_path)
                return response
        else:
            return Response(status=status.HTTP_400_BAD_REQUEST)


class DownloadResultImage(APIView):
    permission_classes = (IsAuthenticated,)
    def get(self, request):
        useridd=request.user.id
        #filename=request.data.get('filename')
        #print(filename)
        dir_path= '/'.join([settings.MEDIA_ROOT,str(useridd), 'comparisons','results'])
        file_path= join(dir_path, 'results.png' )
        mime_type, _ = mimetypes.guess_type(file_path)
        print(file_path)

        if exists(file_path):
            print('hiii')
            with open(file_path, 'rb') as fh:
                response = HttpResponse( fh , content_type=mime_type)
                response['Content-Disposition'] = "attachment; filename=%s" % basename(file_path)
                return response
        else:
            return Response(status=status.HTTP_400_BAD_REQUEST)


class FilesOfUserView(APIView):
    permission_classes = (IsAuthenticated,)
    def get(self, request):
        #print(my_view_id(request))
        useridd=request.user.id

        dir_path= '/'.join([settings.MEDIA_ROOT,str(useridd)])
        if exists(dir_path) and isdir(dir_path):
            filess = [f for f in listdir(dir_path) if isfile(join(dir_path, f))]
        else:
            filess=[]

        #content = simplejson.dumps({"filesList" : filess})
        return JsonResponse(filess, safe=False)
