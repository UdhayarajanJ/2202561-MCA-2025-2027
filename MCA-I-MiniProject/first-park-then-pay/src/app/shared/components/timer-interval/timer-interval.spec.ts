import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimerInterval } from './timer-interval';

describe('TimerInterval', () => {
  let component: TimerInterval;
  let fixture: ComponentFixture<TimerInterval>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TimerInterval]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TimerInterval);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
