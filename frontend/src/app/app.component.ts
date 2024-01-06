import {Component} from "@angular/core";
import {
  AppService
} from "./app.service";
import {
  Campaign
} from "./models/campaign";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['app.component.css'],
  standalone: true,
  imports: [],
  providers: [AppService]
})
export class AppComponent{
  campaigns: Campaign[] = [];

  constructor(private appService: AppService) {

  }
}
