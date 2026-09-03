export interface Investment{
    investmentId?:number;
    name:string;
    description:string;
    type:string;
    purchasePrice:number;
    currentPrice:number;
    quantity:number;
    purchaseDate:string;
    status:string;
}