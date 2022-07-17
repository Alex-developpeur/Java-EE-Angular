import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class UserCrudService {

	constructor(private http: HttpClient) { }

	private baseUrl = 'http://localhost:8080/api/v1';

    getUserInfos(): Observable<any> {
        return this.http.get(`${this.baseUrl}/connection`);
    }

    createResource(path: string, entity: Object): Observable<Object> {
    	return this.http.post(`${this.baseUrl}/${path}`, entity);
    }

    updateResource(path: string, id: number, value: any): Observable<Object> {
    	return this.http.put(`${this.baseUrl}/${path}`, value);
    }

    getResource(path: string, id: number): Observable<any> {
    	return this.http.get(`${this.baseUrl}/${path}/${id}`);
    }

    getResourceList(path: string): Observable<any> {
    	return this.http.get(`${this.baseUrl}/${path}`);
    }

    deleteResource(path: string, id: number): Observable<any> {
    	return this.http.delete(`${this.baseUrl}/${path}/${id}`, { responseType: 'text' });
    }

    getPDF(user: string, entrepriseId: number, numeroDevis: string){
        const url = `${this.baseUrl}/ViewPdf/${user}/${entrepriseId}/${numeroDevis}`;
        const httpOptions = {
            'responseType' : 'arraybuffer' as 'json'
        };
        return this.http.get<any>(url, httpOptions);
    }

    uploadImage(entrepriseId: number, file: File) {
        let body = new FormData();
        body.append("image", file);
        this.http.post(`${this.baseUrl}/UploadImage/${entrepriseId}`, body)
            .subscribe((data) => {console.log(data)},
                error => console.log(error),
                () => { console.log("completed")});
    }


}
