import {Campaign} from "./campaign";
export interface Seller {
  id?: number;
  companyName: string;
  name: string;
  lastName: string;
  balance: number;
  campaigns?: Campaign[];
}
