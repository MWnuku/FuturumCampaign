import { Component } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';


@Component({
  selector: 'app-campaign-display',
  standalone: true,
  imports: [MatButtonModule, MatCardModule],
  templateUrl: './campaign-display.component.html',
  styleUrl: './campaign-display.component.css'
})
export class CampaignDisplayComponent {

}
