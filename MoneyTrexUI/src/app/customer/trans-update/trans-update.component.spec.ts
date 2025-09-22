import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransUpdateComponent } from './trans-update.component';

describe('TransUpdateComponent', () => {
  let component: TransUpdateComponent;
  let fixture: ComponentFixture<TransUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TransUpdateComponent]
    });
    fixture = TestBed.createComponent(TransUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
