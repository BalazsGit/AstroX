import { Component, NgZone } from '@angular/core';

// Declare the JavaScript function outside the component class
declare global {
  interface Window {
    callJavaFunction: () => void;
    closeWindow: () => void;
    toggleFullscreen: () => void;
    cefQuery: (query: any) => void; // You may need to adjust the type of 'query' as per your needs
  }
}

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.scss']
})
export class MainpageComponent {

responseMessage: string = "";

  constructor(private ngZone: NgZone) {
    // Export the JavaScript function
    window.callJavaFunction = this.callJavaFunction;
    window.closeWindow = this.closeWindow;
    window.toggleFullscreen = this.toggleFullscreen;
  }

  // Angular component or service
  callJavaFunction() {
    const component = this;
    //component.responseMessage="Request sent";
    // Use cefQuery to send a query to your Java backend
    window.cefQuery({
      request: 'myQuery', // Should match the query name defined in Java
      onSuccess: function(response: any) {
        component.ngZone.run(() => {
          component.responseMessage = "Success";
          console.log('Java function called successfully');
        });
      },
      onFailure: function(error_code: any, error_message: any) {
        component.ngZone.run(() => {
          component.responseMessage="Error";
          console.error('Error calling Java function: ' + error_message);
        });
      }
    });
  }

  closeWindow() {
    const component = this;
    //component.responseMessage="Request sent";
    // Use cefQuery to send a query to your Java backend
    window.cefQuery({
      request: 'closeWindow', // Should match the query name defined in Java
      onSuccess: function(response: any) {
        component.ngZone.run(() => {
          component.responseMessage = "Success";
          console.log('Java function called successfully');
        });
      },
      onFailure: function(error_code: any, error_message: any) {
        component.ngZone.run(() => {
          component.responseMessage="Error";
          console.error('Error calling Java function: ' + error_message);
        });
      }
    });
   }

  toggleFullscreen() {
    const component = this;
    //component.responseMessage="Request sent";
    // Use cefQuery to send a query to your Java backend
    window.cefQuery({
      request: 'toggleFullscreen', // Should match the query name defined in Java
      onSuccess: function(response: any) {
        component.ngZone.run(() => {
          component.responseMessage = "Success";
          console.log('Java function called successfully');
        });
      },
      onFailure: function(error_code: any, error_message: any) {
        component.ngZone.run(() => {
          component.responseMessage="Error";
          console.error('Error calling Java function: ' + error_message);
        });
      }
    });
  }

}
