import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(investments: any[], filterBy:any): any[]{
    if(!investments || !filterBy){
      return investments;
    }
    if(filterBy === 'All Types'){
      return investments;
    }
    if(filterBy === 'Stock'){
      return investments.filter((a)=>a.type == 'Stock')
    }
    if(filterBy === 'Crypto'){
      return investments.filter((a)=>a.type == 'Crypto')
    }
    if(filterBy === 'Real Estate'){
      return investments.filter((a)=>a.type == 'Real Estate')
    }
    if(filterBy === 'Mutual Funds'){
      return investments.filter((a)=>a.type == 'Mutual Funds')
    }
  }

}
