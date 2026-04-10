import { Directive, ElementRef, HostListener, Input } from '@angular/core';

/**
 * Directiva personalizada: resalta una fila/celda al pasar el cursor.
 * Uso: <td [appHighlight]="'#e3f2fd'">
 */
@Directive({
  selector: '[appHighlight]',
  standalone: true
})
export class HighlightDirective {
  @Input() appHighlight = '#e3f2fd';

  constructor(private el: ElementRef) {}

  @HostListener('mouseenter')
  onMouseEnter(): void {
    this.el.nativeElement.style.backgroundColor = this.appHighlight;
    this.el.nativeElement.style.transition = 'background-color 0.3s ease';
  }

  @HostListener('mouseleave')
  onMouseLeave(): void {
    this.el.nativeElement.style.backgroundColor = '';
  }
}
