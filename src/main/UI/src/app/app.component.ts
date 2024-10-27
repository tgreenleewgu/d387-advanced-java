import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {HttpClient, HttpResponse,HttpHeaders} from "@angular/common/http";
import {Observable, pipe} from 'rxjs';
import {map} from "rxjs/operators";





@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  englishWelcomeMessage$!: Observable<string>;
  frenchWelcomeMessage$!: Observable<string>;

  constructor(private httpClient:HttpClient){}

  // convertedTimesZones: string = '';

  private baseURL:string='http://localhost:8080';

  private getUrl:string = this.baseURL + '/room/reservation/v1/';
  private postUrl:string = this.baseURL + '/room/reservation/v1';
  public submitted!:boolean;
  roomsearch! : FormGroup;
  rooms! : Room[];
  request!:ReserveRoomRequest;
  currentCheckInVal!:string;
  currentCheckOutVal!:string;
  convertedTimesZones: string = '';

    ngOnInit(){

      // this.fetchTimes();
      this.setPresentationTimes();



      this.englishWelcomeMessage$ = this.httpClient.get(this.baseURL + '/welcome-en?lang=en-US', { responseType: 'text' });
      this.frenchWelcomeMessage$ = this.httpClient.get(this.baseURL + '/welcome-fr?lang=fr-CA', { responseType: 'text' });

      // this.englishWelcomeMessage$ = this.httpClient.get(this.baseURL + '/welcome?lang=en-US', { responseType: 'text' }).pipe(map(response => response as string));
      //
      //
      // this.frenchWelcomeMessage$ = this.httpClient.get(this.baseURL + '/welcome?lang=fr-CA', { responseType: 'text' }).pipe(map(response => response as string));

      this.roomsearch= new FormGroup({
        checkin: new FormControl(' '),
        checkout: new FormControl(' ')
      });

 //     this.rooms=ROOMS;


    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    // subscribe to the stream
    roomsearchValueChanges$.subscribe(x => {
      this.currentCheckInVal = x.checkin;
      this.currentCheckOutVal = x.checkout;
    });
  }

  setPresentationTimes(): void {
    // Define the presentation date and time in Eastern Time (ET)
    const eventDate = new Date('2024-11-14T15:00:00-05:00'); // 3:00 PM ET on Nov 14, 2024

    // Convert to UTC
    const utcTime = eventDate.toLocaleString('en-US', { timeZone: 'UTC', hour: '2-digit', minute: '2-digit', hour12: true });

    // Convert to Mountain Time (MT)
    const mountainTime = eventDate.toLocaleString('en-US', { timeZone: 'America/Denver', hour: '2-digit', minute: '2-digit', hour12: true });

    // Convert to Eastern Time (ET)
    const easternTime = eventDate.toLocaleString('en-US', { timeZone: 'America/New_York', hour: '2-digit', minute: '2-digit', hour12: true });

    // Combine all time zones into a formatted string
    this.convertedTimesZones = `Eastern Time: ${easternTime}, Mountain Time: ${mountainTime}, UTC: ${utcTime}`;
  }

  // onSubmit({ value, valid }: { value: Roomsearch, valid: boolean }) {
  //   this.getAll().subscribe(
  //     rooms => { console.log(Object.values(rooms)[0]); this.rooms = <Room[]>Object.values(rooms)[0]; }
  //   );
  // }

  //   fetchTimes() {
  //     this.httpClient.get('http://localhost:8080/api/time/convert', {responseType: 'text'}).subscribe(
  //       (res: string) => {
  //         this.convertedTimesZones = res;
  //       },
  //       (error: any) => {
  //         console.error(error);
  //     }
  //   );
  // }

  // fetchTimes() {
  //   this.httpClient.get(this.baseURL + '/api/time/convert', {responseType: 'text'}).subscribe(
  //     (res: string) => {
  //       this.convertedTimesZones = res;
  //     },
  //     (error: any) => {
  //       console.error(error);
  //   }
  // );
  // }

    onSubmit({value,valid}:{value:Roomsearch,valid:boolean}){
      this.getAll().subscribe(

        rooms => {console.log(Object.values(rooms)[0]);this.rooms=<Room[]>Object.values(rooms)[0]; }


      );
    }
    reserveRoom(value:string){
      this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);

      this.createReservation(this.request);
    }
    createReservation(body:ReserveRoomRequest) {
      let bodyString = JSON.stringify(body); // Stringify payload
      let headers = new Headers({'Content-Type': 'application/json'}); // ... Set content type to JSON
     // let options = new RequestOptions({headers: headers}); // Create a request option

     const options = {
      headers: new HttpHeaders().append('key', 'value'),

    }

      this.httpClient.post(this.postUrl, body, options)
        .subscribe(res => console.log(res));
    }

  /*mapRoom(response:HttpResponse<any>): Room[]{
    return response.body;
  }*/


    getAll(): Observable<any> {


       return this.httpClient.get(this.baseURL + '/room/reservation/v1?checkin='+ this.currentCheckInVal + '&checkout='+this.currentCheckOutVal, {responseType: 'json'});
    }

  }



export interface Roomsearch{
    checkin:string;
    checkout:string;
  }




export interface Room{
  id:string;
  roomNumber:string;
  price:string;
  links:string;

}
export class ReserveRoomRequest {
  roomId:string;
  checkin:string;
  checkout:string;

  constructor(roomId:string,
              checkin:string,
              checkout:string) {

    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }
}

/*
var ROOMS: Room[]=[
  {
  "id": "13932123",
  "roomNumber" : "409",
  "price" :"20",
  "links" : ""
},
{
  "id": "139324444",
  "roomNumber" : "509",
  "price" :"30",
  "links" : ""
},
{
  "id": "139324888",
  "roomNumber" : "609",
  "price" :"40",
  "links" : ""
}
] */

