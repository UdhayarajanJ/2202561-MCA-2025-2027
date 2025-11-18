import { ChangeDetectorRef, Component, input, OnDestroy, OnInit } from '@angular/core';
import { interval, startWith, Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-timer-interval',
  imports: [],
  templateUrl: './timer-interval.html',
  styleUrl: './timer-interval.scss',
  standalone: true
})
export class TimerInterval implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();

  public checkInDateTime = input<string | null>(null);
  public elapsedTime: string = '00:00:00';

  constructor(private cdr: ChangeDetectorRef) { }

  public ngOnInit(): void {
    interval(1000)
      .pipe(startWith(0), takeUntil(this.destroy$))
      .subscribe(() => this.updateElapsedTime());
  }

  private updateElapsedTime(): void {
    const value = this.checkInDateTime();
    if (!value) {
      this.elapsedTime = '00:00:00';
      return;
    }

    const checkIn = new Date(value);
    const now = new Date();
    const diff = now.getTime() - checkIn.getTime();

    if (diff < 0) {
      this.elapsedTime = '00:00:00';
      return;
    }

    const hours = Math.floor(diff / (1000 * 60 * 60));
    const minutes = Math.floor((diff / (1000 * 60)) % 60);
    const seconds = Math.floor((diff / 1000) % 60);

    this.elapsedTime = [
      hours.toString().padStart(2, '0'),
      minutes.toString().padStart(2, '0'),
      seconds.toString().padStart(2, '0')
    ].join(':');

    this.cdr.markForCheck();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
