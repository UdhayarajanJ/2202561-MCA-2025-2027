import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private http = inject(HttpClient);
  private baseUrl = environment.API_BASE_URL;

  private getHeaders(extraHeaders?: Record<string, string>): HttpHeaders {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });

    if (extraHeaders) {
      for (const key in extraHeaders) {
        headers = headers.set(key, extraHeaders[key]);
      }
    }

    return headers;
  }

  public get<T>(endpoint: string, params?: Record<string, any>, headers?: Record<string, string>): Observable<T> {
    const httpParams = new HttpParams({ fromObject: params || {} });
    return this.http.get<T>(`${this.baseUrl}/${endpoint}`, {
      headers: this.getHeaders(headers),
      params: httpParams
    });
  }

  public post<T>(endpoint: string, body: any, headers?: Record<string, string>): Observable<T> {
    return this.http.post<T>(`${this.baseUrl}/${endpoint}`, body, {
      headers: this.getHeaders(headers)
    });
  }

  public put<T>(endpoint: string, body: any, headers?: Record<string, string>): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}/${endpoint}`, body, {
      headers: this.getHeaders(headers)
    });
  }

  public patch<T>(endpoint: string, body: any, headers?: Record<string, string>): Observable<T> {
    return this.http.patch<T>(`${this.baseUrl}/${endpoint}`, body, {
      headers: this.getHeaders(headers)
    });
  }

  public delete<T>(endpoint: string, params?: Record<string, any>, headers?: Record<string, string>): Observable<T> {
    const httpParams = new HttpParams({ fromObject: params || {} });
    return this.http.delete<T>(`${this.baseUrl}/${endpoint}`,{
      headers: this.getHeaders(headers),
      params: httpParams
    });
  }
}
