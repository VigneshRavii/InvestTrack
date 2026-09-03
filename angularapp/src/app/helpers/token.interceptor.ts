import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    const token = localStorage.getItem('token');
    const excludedUrls = ['/api/login', '/api/register'];

    const shouldExclude =excludedUrls.some(url=>request.url.includes(url));

    if(token && !shouldExclude) {
      const cloned = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${token}`)
        
      });
      console.log(request.headers);
      
      return next.handle(cloned);
    }

    return next.handle(request);
  }
}
