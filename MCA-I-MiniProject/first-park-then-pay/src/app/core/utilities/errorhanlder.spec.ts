import { TestBed } from '@angular/core/testing';

import { Errorhanlder } from './errorhanlder';

describe('Errorhanlder', () => {
  let service: Errorhanlder;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Errorhanlder);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
