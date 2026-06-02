import { Component, signal } from '@angular/core';
import { AdminLayout } from './layouts/admin-layout/admin-layout';

@Component({
  selector: 'app-root',
  imports: [AdminLayout],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
}
