import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Campaign } from './models/campaign';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  private readonly url = 'http://localhost:8080/campaign'

  constructor(private http: HttpClient) { }

  getAllCampaigns(){
    return this.http.get<Campaign[]>(this.url);
  }

  getCampaignById(id: number){
    return this.http.get<Campaign>(this.url);
  }

  
}
