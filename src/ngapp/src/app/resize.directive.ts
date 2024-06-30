import { Directive, ElementRef, Output, EventEmitter, AfterViewInit, OnDestroy, Input } from '@angular/core';

@Directive({
  selector: '[appResize]'
})
export class ResizeDirective implements AfterViewInit, OnDestroy {
  //@Input('obsIntersectParent') parent;
  @Output() resize: EventEmitter<DOMRect> = new EventEmitter<DOMRect>();
  @Output() intersection: EventEmitter<DOMRect> = new EventEmitter<DOMRect>();
  @Output() mutation: EventEmitter<DOMRect> = new EventEmitter<DOMRect>();

  private resizeObserver!: ResizeObserver; // Use definite assignment assertion
  private mutationObserver!: MutationObserver;
  private intersectionObserver!: IntersectionObserver;


  constructor(private el: ElementRef) {}

  ngAfterViewInit(): void {
    this.resizeObserver = new ResizeObserver((entries) => {
      //replaced with boundingClientRectObserver
      return
      //const rect: DOMRect = entries[0].contentRect;
      const rect = this.el.nativeElement.getBoundingClientRect();
      //console.log('Resize_');
      this.resize.emit(rect);
    });

    this.mutationObserver = new MutationObserver((entries) => {
      //replaced with boundingClientRectObserver
      return
      const rect = this.el.nativeElement.getBoundingClientRect();
      //console.log('Mutations_');
      this.mutation.emit(rect);
    });


// Set up the Intersection Observer
    this.intersectionObserver = new IntersectionObserver((entries) => {
      //replaced with boundingClientRectObserver
      return
      const rect = this.el.nativeElement.getBoundingClientRect();
      console.log('Intersection_');
      this.intersection.emit(rect);
        //}
      //});
    });


    // Observe changes in the size of the element
    this.resizeObserver.observe(this.el.nativeElement);

    // Start observing the element
    this.intersectionObserver.observe(this.el.nativeElement);

    // Observe mutations
    this.mutationObserver.observe(this.el.nativeElement);

        // Observe mutations (e.g., position changes) in the element
        /*this.mutationObserver.observe(this.el.nativeElement, {
          attributes: true, // Watch for attribute changes
          attributeFilter: ['style'], // Specify the attribute(s) to observe (e.g., style changes)
        });*/
  }

  ngOnDestroy(): void {
    // Cleanup when the directive is destroyed
    if (this.resizeObserver) {
      this.resizeObserver.disconnect();
    }

    if (this.mutationObserver) {
      this.mutationObserver.disconnect();
    }

    if (this.intersectionObserver) {
      this.intersectionObserver.disconnect();
    }
  }
}

/*
import { Directive, ElementRef, HostListener, Output, EventEmitter, OnDestroy } from '@angular/core';
import { fromEvent } from 'rxjs';
import { debounceTime } from 'rxjs/operators';

@Directive({
  selector: '[appResize]'
})
export class ResizeDirective {
  @Output() resize: EventEmitter<DOMRect> = new EventEmitter();

  constructor(private el: ElementRef) {}

  @HostListener('window:resize', ['$event'])
  onResize(event: Event): void {
    const rect: DOMRect = this.el.nativeElement.getBoundingClientRect();
    this.resize.emit(rect);
  }
}
*/
/*
const entriesMap = new WeakMap();

const ro = new ResizeObserver(entries => {
  for (const entry of entries) {
    if (entriesMap.has(entry.target)) {
      const comp = entriesMap.get(entry.target);
      comp._resizeCallback(entry);
    }
  }
});

@Directive({ selector: '[resizeObserver]' })
export class ResizeDirective implements OnDestroy {
  @Output()
  resize = new EventEmitter();

  constructor(private el: ElementRef) {
    const target = this.el.nativeElement;
    entriesMap.set(target, this);
    ro.observe(target);
  }

  _resizeCallback(entry) {
    console.log(entry.contentRect);
    this.resize.emit(entry);
  }

  ngOnDestroy() {
    const target = this.el.nativeElement;
    ro.unobserve(target);
    entriesMap.delete(target);
  }
}
*/
