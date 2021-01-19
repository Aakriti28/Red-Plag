import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
    file: File;

    files;

    imageToShow: any;

    isImageLoading : boolean;
    isResLoading : boolean;
    isSingleFile : boolean;


    list_of_files : string[];
    selectedFile : string;

    list_of_boils : string[];
    selectedBoil : string;




  constructor(private userSer: UserServiceService, private router : Router) { }

  log = this.userSer.loggedIn;
  ngOnInit(){
    this.isSingleFile=true;
    this.list_of_boils=[];
    this.listFiles();
    console.log(this.list_of_files);
    this.isImageLoading=false;
    this.isResLoading=false;
    this.files = {
      filename : '' ,
      boilname : '' ,
        };
    //this.userSer.getUserClaims().subscribe((data: any) => {
     // this.userClaims = data;

    //});

  }

  createImageFromBlob(image : Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
       this.imageToShow = reader.result;
    }, false);

    if (image) {
       reader.readAsDataURL(image);
    }
 }


  downloadFile_func(data){

    console.log(data.headers.get('content-type'))
    const blob = new Blob([data.body], { type: data.headers.get('content-type') });
    //const url= window.URL.createObjectURL(blob);
    //console.log('HIII')
    //window.open(url);


    var filename = "";
    var disposition = data.headers.get('content-disposition');
    if (disposition && disposition.indexOf('attachment') !== -1) {
        var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
        var matches = filenameRegex.exec(disposition);
        if (matches != null && matches[1]) {
          filename = matches[1].replace(/['"]/g, '');
        }
    }

    console.log(filename)



    var url = window.URL.createObjectURL(blob);
    var anchor = document.createElement("a");
    anchor.download = filename;
    anchor.href = url;
    anchor.click();

  }


  compareFiles(){
    this.isResLoading=true;
    this.userSer.compareFilesService(this.files).subscribe(
     ( response) => {
        //console.log(response)
        //console.log()
        //console.log(response.headers.get('content-type'))
        this.resImage();
        alert('Downloading The Results...')
        this.downloadFile_func(response)
        this.selectedBoil = '';
        this.files.boilname = '';
        this.isResLoading=false;




      },
      error => {console.log('error', error), alert('error')
      this.isResLoading=false;

    }
    );
  }

  downloadFiles(){
    this.userSer.downloadFilesService(this.files).subscribe(
      response => {
        //console.log(response)
        console.log(response.headers.get('content-disposition'))
        alert('Downloading File...')
        this.downloadFile_func(response)
      },
      error => {console.log('error', error), alert('error')
    }
    );
  }


  deleteFiles(){
    this.userSer.deleteFileService(this.files).subscribe(
      response => {
        console.log(response)
        alert('File Deleted')
        this.listFiles();
      },
      error => {console.log('error', error), alert('error')
    }
    );
    if(this.selectedBoil == this.selectedFile){
      this.selectedBoil = undefined;
      this.files.boilname = '';
    }
    this.selectedFile = undefined;
    this.files.filename = '';
  }



  listFiles(){
    this.userSer.listFilesService().subscribe(
      response => {
        //console.log(response)
        //console.log(response)
        this.list_of_files = response;
        this.list_of_boils =[];
        console.log(this.list_of_files)


        for(var i = 0; i < this.list_of_files.length; i++)
{
    if(this.list_of_files[i].endsWith('.cpp') || this.list_of_files[i].endsWith('.py') || this.list_of_files[i].endsWith('.java')){
      this.list_of_boils.push(this.list_of_files[i])
    }
}
        //alert('Suckcess')

      },
      error => {console.log('error', error), alert('error')
    }
    );
  }


  onSelect(file : string){

    //console.log(this.selectedFile)
  if(this.selectedFile == file){
  	this.selectedFile = undefined;
  	this.files.filename = '';
  }
  else{
    this.selectedFile = file;
    this.files.filename = this.selectedFile;
	}
  if(this.files.filename.endsWith('.zip')||this.files.filename.endsWith('.tar')||this.files.filename.endsWith('.tar.gz')){
    this.isSingleFile = false;
  }
  else{
    this.isSingleFile = true;
  }
	// this.selectedFile = file;
	// this.files.filename = this.selectedFile;
    this.isImageLoading=false;

    //console.log(this.selectedFile)

  }

  onBoilSelect(file : string){

    //console.log(this.selectedFile)
    if (this.selectedBoil == file ){
    	this.selectedBoil = undefined;
    	this.files.boilname = '';
    }
    else{
	    this.selectedBoil = file;
	    this.files.boilname = this.selectedBoil;
	}
	// this.selectedBoil = file;
	// this.files.boilname = this.selectedBoil;


    //this.isImageLoading=false;

    console.log(this.selectedBoil)

  }


  Logout() {
    //alert(this.userSer.loggedIn);
    this.userSer.LogoutUser();
    //alert(this.userSer.loggedIn);
  }

  onFile(event: any) {
    this.file = event.target.files[0];
  }
 uploadFile() {
   const uploadData = new FormData();
   uploadData.append('file', this.file, this.file.name);
  this.userSer.uploadFile_service(uploadData).subscribe(
    response => {
      console.log(response)
      alert('File '+ this.file.name +  ' has been uploaded')
      this.listFiles();
      console.log(this.list_of_files)
    },
    error => {console.log('error', error), alert('error')
  }
  );
 }


  resImage(){
  this.userSer.downloadResImService().subscribe(
    response => {
      let blob = new Blob([response.body], {type: 'image/png'});
      this.createImageFromBlob(blob);
        this.isImageLoading = true;
      }, error => {
        this.isImageLoading = false;
        console.log(error);
      });
}



}
