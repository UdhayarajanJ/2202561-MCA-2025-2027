import { Component,  signal } from '@angular/core';
import { Header } from './shared/components/header/header';
import { Menu } from "./shared/components/menu/menu";
import { RouterOutlet } from '@angular/router';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-root',
  imports: [Header, Menu, RouterOutlet,ToastModule],
  templateUrl: './app.html',
  styleUrl: './app.scss',
  standalone:true
})
export class App {
  protected readonly title = signal('first-park-then-pay');
}
