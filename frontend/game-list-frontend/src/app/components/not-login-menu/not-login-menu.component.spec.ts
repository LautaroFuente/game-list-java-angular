import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotLoginMenuComponent } from './not-login-menu.component';

describe('NotLoginMenuComponent', () => {
  let component: NotLoginMenuComponent;
  let fixture: ComponentFixture<NotLoginMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotLoginMenuComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotLoginMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
