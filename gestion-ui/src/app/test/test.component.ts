import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup } from "@angular/forms";
import { UserCrudService } from '../core';

@Component({
	selector: 'app-test',
	templateUrl: './test.component.html',
	styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {

    selectedFile: File;
    retrievedImage: any;
    base64Data: any;
    retrieveResonse: any;
    message: string;
    imageName: any;

    form: FormGroup;
    file: File;

    constructor(private fb: FormBuilder, private http: HttpClient,
                private userCrud: UserCrudService) { }

    ngOnInit() {
        this.createForm();
    }

    // Instantiate an AbstractControl from a user specified configuration
    createForm() {
        this.form = this.fb.group({
            file_upload: null
        });
    }

    //Gets called when the user selects an image
    public onFileChanged(event) {
        //Select File
        this.selectedFile = event.target.files[0];
    }
    //Gets called when the user clicks on submit to upload the image
    onUpload() {
        console.log(this.selectedFile);
        const HttpUploadOptions = {
            headers: new HttpHeaders({ "Content-Type": "multipart/form-data" })
        }
        //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
        const uploadImageData = new FormData();
        uploadImageData.append('file', this.selectedFile, this.selectedFile.name);

        //Make a call to the Spring Boot Application to save the image
        this.http.post('http://localhost:8080/api/v1/upload', uploadImageData, HttpUploadOptions)
            .subscribe((data) => {
                console.log(data)
            },error => console.log(error));
    }    //Gets called when the user clicks on retieve image button to get the image from back end

    getImage() {
        //Make a call to Sprinf Boot to get the Image Bytes.
        this.http.get('http://localhost:8080/api/v1/upload/' + this.imageName)
        .subscribe(
            res => {
                this.retrieveResonse = res;
                this.base64Data = this.retrieveResonse.picByte;
                this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
            }
            );
    }

    fileChange(event: any) {
        // Instantiate an object to read the file content
        let reader = new FileReader();
        // when the load event is fired and the file not empty
        if(event.target.files && event.target.files.length > 0) {
            // Fill file variable with the file content
            this.file = event.target.files[0];
        }
    }

    // Upload the file to the API
    upload() {
        console.log("Création devis");

        this.userCrud.uploadImage(4, this.file);
        /*
        const HttpUploadOptions = {
            headers: new HttpHeaders({ "Content-Type": "multipart/form-data" })
        }
        const options = {} as any;
        // Instantiate a FormData to store form fields and encode the file
        let body = new FormData();
        // Add file content to prepare the request
        body.append("file", this.file);
        // Launch post request
        this.http.post('http://localhost:8080/api/v1/upload', body)
        .subscribe(
            // Admire results
            (data) => {console.log(data)},
            // Or errors :-(
            error => console.log(error),
            // tell us if it's finished
            () => { console.log("completed") }
            );
            */
    }
}
