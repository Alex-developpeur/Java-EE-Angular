import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsProComponent } from './details-pro.component';

describe('DetailsProComponent', () => {
  let component: DetailsProComponent;
  let fixture: ComponentFixture<DetailsProComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsProComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsProComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
