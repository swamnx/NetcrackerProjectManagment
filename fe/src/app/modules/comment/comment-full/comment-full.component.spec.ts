import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentFullComponent } from './comment-full.component';

describe('CommentFullComponent', () => {
  let component: CommentFullComponent;
  let fixture: ComponentFixture<CommentFullComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentFullComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentFullComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
