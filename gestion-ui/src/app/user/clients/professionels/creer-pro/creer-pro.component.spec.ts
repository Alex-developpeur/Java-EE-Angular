import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreerProComponent } from './creer-pro.component';

describe('CreerProComponent', () => {
  let component: CreerProComponent;
  let fixture: ComponentFixture<CreerProComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreerProComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreerProComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
