import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(investments: any[], searchText:any): unknown {
    if(!investments || !searchText){
      return investments;
    }
    searchText=searchText.toLowerCase();
    return investments.filter((a)=>a.name.toLowerCase().includes(searchText));
  }

}
