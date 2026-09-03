import { JsonPipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchfilter'
})
export class SearchfilterPipe implements PipeTransform {

  transform(value: any[], searchfilter: any): any[] {
    if(!value) return null;
    if(!searchfilter) return value;
    searchfilter=searchfilter.toLowerCase();
    return value.filter((result) => {
      return JSON.stringify(result).toLowerCase().includes(searchfilter);
    });
    
   
  } 

}
