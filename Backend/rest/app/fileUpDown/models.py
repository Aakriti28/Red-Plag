from django.db import models

# Create your models here.
def upload_path(instance,filename):
    return '/'.join([str(instance.userid), filename])
class File(models.Model):
    userid=models.UUIDField( blank=False, null=False, default=None)
    file = models.FileField(blank=False, null=False, upload_to=upload_path)

    def delete(self, *args, **kwargs):
        storage, path = self.file.storage, self.file.path
        storage.delete(path)
        super(File, self).delete(*args, **kwargs)
