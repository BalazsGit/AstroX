import { Directive, Component, NgZone, ElementRef, ViewChild, HostListener, Input, OnDestroy, OnInit, Output, EventEmitter } from '@angular/core';
import { Renderer2 } from '@angular/core';
import { ResizeDirective } from '../resize.directive'; // Adjust the path
import { FormBuilder } from '@angular/forms';
import { MatSidenav } from '@angular/material/sidenav';


import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';
import { NgIf } from '@angular/common';

//import ResizeObserver from 'resize-observer-polyfill';
//import { ResizeObserverDirective } from './resize-observer.directive';

//declare const ResizeObserver: any; // Declare ResizeObserver

var prevWidth: number = 0;
var prevHeight: number = 0;
var width: number = 0;
var height: number = 0;
var left: number = 0;
var right: number = 0;
var top: number = 0;
var bottom: number = 0;

// Declare the JavaScript function outside the component class
declare global {
  interface Window {
    callJavaFunction: () => void;
    closeWindow: () => void;
    toggleFullscreen: () => void;
    resizeBrowserPanel: (width: number, height: number, left: number, right: number, top: number, bottom: number) => void;
    cefQuery: (query: any) => void; // You may need to adjust the type of 'query' as per your needs
  }
}

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.scss']
})

/*
@Directive({
  selector: 'iframe[resize]'
})
*/
export class MainpageComponent implements OnInit {

  @ViewChild('sidenav') sidenav!: MatSidenav;

  reason = '';

  close(reason: string) {
    console.log("close");
    this.reason = reason;
    this.sidenav.close();
  }

    options = this.formBuilder.group({
    bottom: 0,
    fixed: false,
    top: 0,
    });

  responseMessage: string = "";

  iframe1Src: string = 'https://www.example.com';
  iframe2Src: string = 'https://www.example.com';

  //@ViewChild('iframe1') iframe1: ElementRef;
  //@ViewChild('iframe2') iframe2: ElementRef;

  @ViewChild('iframe1') iframe1!: ElementRef;
  @ViewChild('iframe2') iframe2!: ElementRef;

  //@Input() iframe: HTMLIFrameElement;

  //const iframe1Element = this.iframe1.nativeElement;
  //const iframe2Element = this.iframe2.nativeElement;
  constructor( private ngZone: NgZone,
               private renderer: Renderer2,
               private elementRef: ElementRef,
               private formBuilder: FormBuilder) {
    // Export the JavaScript function
    window.callJavaFunction = this.callJavaFunction;
    window.closeWindow = this.closeWindow;
    window.toggleFullscreen = this.toggleFullscreen;
    window.resizeBrowserPanel = this.resizeBrowserPanel;
    //this.iframe1 = new ElementRef(null);
    //this.iframe2 = new ElementRef(null);
    prevWidth = window.innerWidth; // Initialize with the initial width
    prevHeight = window.innerHeight; // Initialize with the initial height

  }
    ngOnInit(): void {
        throw new Error('Method not implemented.');
    }

    onResize(rect: DOMRect): void {
      // Handle the resize event here
      if ((rect.width != prevWidth && rect.width != undefined) || (rect.height != prevHeight && rect.height != undefined)) {
        //console.log('width:', rect.width, 'height:', rect.height);
        //console.log('left:', rect.left, 'right:', rect.right);
        //console.log('top:', rect.top, 'bottom:', rect.bottom);
        //console.log('prev. width:', prevWidth, 'prev. height:', prevHeight);
        prevWidth = rect.width;
        prevHeight = rect.height;

        width = rect.width;
        height = rect.height;
        left = rect.left;
        right = rect.right;
        top = rect.top;
        bottom = rect.bottom;
        this.resizeBrowserPanel(width, height, left, right, top, bottom);
        console.log('Resize');
      }
  }

  onMutation(rect: DOMRect): void {
    // Handle the resize event here
    //if ((rect.width != prevWidth && rect.width != undefined) || (rect.height != prevHeight && rect.height != undefined)) {
    //console.log('width:', rect.width, 'height:', rect.height);
    //console.log('left:', rect.left, 'right:', rect.right);
    //console.log('top:', rect.top, 'bottom:', rect.bottom);
    //console.log('prev. width:', prevWidth, 'prev. height:', prevHeight);
    prevWidth = rect.width;
    prevHeight = rect.height;

    width = rect.width;
    height = rect.height;
    left = rect.left;
    right = rect.right;
    top = rect.top;
    bottom = rect.bottom;
    this.resizeBrowserPanel(width, height, left, right, top, bottom);
    //}
    console.log("Mutation");
  }

    onIntersection(rect: DOMRect): void {
      // Handle the resize event here
      //if ((rect.width != prevWidth && rect.width != undefined) || (rect.height != prevHeight && rect.height != undefined)) {
       //console.log('width:', rect.width, 'height:', rect.height);
        //console.log('left:', rect.left, 'right:', rect.right);
        //console.log('top:', rect.top, 'bottom:', rect.bottom);
        //console.log('prev. width:', prevWidth, 'prev. height:', prevHeight);
        prevWidth = rect.width;
        prevHeight = rect.height;

        width = rect.width;
        height = rect.height;
        left = rect.left;
        right = rect.right;
        top = rect.top;
        bottom = rect.bottom;
        this.resizeBrowserPanel(width, height, left, right, top, bottom);
      //}
      console.log("Intersection");
    }

/*
    @HostListener('window:resize', ['$event'])
    onResize(event: Event): void {
      this.resizeIframe();
    }

    ngOnInit(): void {
      // Initialize iframe
      this.iframe = this.elementRef.nativeElement;
      this.resizeIframe();
    }
*/
/*
    ngOnInit() {
      const observer = new ResizeObserver(entries => {
        const width = entries[0].contentRect.width;
        console.log("Width: " + width);
      });
      observer.observe(this.iframe1.nativeElement);
      }
*/
/*
ngOnInit(): void {
    // Use ResizeObserver to observe changes in element size
    const observer = new ResizeObserver(entries => {
      // Handle size changes here
      entries.forEach(entry => {
        // Entry.target is the observed element
        console.log('Element size changed:', entry.target);
      });
    });

    // Get the native element to observe (for example, a div)
    const elementToObserve = this.iframe1.nativeElement;

    // Start observing the element
    this.ngZone.runOutsideAngular(() => {
      observer.observe(elementToObserve);
    });
  }
  */
/*
onResize(ev) {
    if (ev.contentRect.width < 500) {
      this.renderer.setStyle(ev.target, 'background', 'red');
    } else {
      this.renderer.removeStyle(ev.target, 'background');
    }
  }
*/
  loadPage1() {
    const iframe1Element = this.iframe1.nativeElement as HTMLIFrameElement;
    iframe1Element.src = this.iframe1Src;
  }

  loadPage2() {
    const iframe2Element = this.iframe2.nativeElement as HTMLIFrameElement;
    iframe2Element.src = this.iframe2Src;
  }

  loadPage(url: string, iframe: ElementRef) {
    iframe.nativeElement.src = url;
  }

  navigateForward(iframe: ElementRef) {
    iframe.nativeElement.contentWindow.history.forward();
  }

  navigateBackward(iframe: ElementRef) {
    iframe.nativeElement.contentWindow.history.back();
  }
/*
  // Create a new ResizeObserver instance
  const resizeObserver = new ResizeObserver(entries => {
    for (const entry of entries) {
      const element = entry.target as HTMLElement;
      const { width, height } = element.getBoundingClientRect();
      // Handle size changes here
      console.log(`Element size changed to width: ${width}px, height: ${height}px`);
    }
  });

  // Specify the element you want to observe
  const elementToObserve = document.getElementById('iframe1');

  // Start observing the element
  resizeObserver.observe(this.iframe1.nativeElement);
*/
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

  resizeBrowserPanel(width: number, height: number, left: number, right: number, top: number, bottom: number) {
      // Construct the request string without query parameters
      const component = this;
      const request = "resizeBrowserPanel";

      // Add the width and height as parameters in an object
      const params = {
        width: width,
        height: height,
        left: left,
        right: right,
        top: top,
        bottom: bottom
      };

      // Use JSON.stringify to convert the parameters to a JSON string
      const queryParams = JSON.stringify(params);

      // Append the parameters to the request string
      const fullRequest = `${request}?${queryParams}`;
      //component.responseMessage="Request sent";
      // Use cefQuery to send a query to your Java backend
      window.cefQuery({
        request: fullRequest, // Should match the query name defined in Java
        onSuccess: function(response: any) {
          component.ngZone.run(() => {
            component.responseMessage = "Success";
            //console.log('Successful resize event');
          });
        },
        onFailure: function(error_code: any, error_message: any) {
          component.ngZone.run(() => {
            component.responseMessage = "Error" + error_message;
            //console.error('Error calling Java function: ' + error_message);
          });
        }
      });
    }

}
