import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

    SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
    private nameSource = new BehaviorSubject<string>(sessionStorage.getItem(this.SESSION_ATTRIBUTE_NAME));
    currentName = this.nameSource.asObservable();

	private baseUrl = 'http://localhost:8080/api/v1';

    constructor(private http: HttpClient) { }

    setName(name: string) {
        this.nameSource.next(name);
    }

    getAdminInfos(): Observable<any> {
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
}
