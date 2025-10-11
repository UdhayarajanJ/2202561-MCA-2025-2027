import { Component,  signal } from '@angular/core';
import { Header } from './shared/components/header/header';
import { Menu } from "./shared/components/menu/menu";
import { RouterOutlet } from '@angular/router';
import { Loader } from "./shared/components/loader/loader";

@Component({
  selector: 'app-root',
  imports: [Header, Menu, RouterOutlet, Loader],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  protected readonly title = signal('first-park-then-pay');
}
