import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectFullComponent } from './project-full.component';

describe('ProjectFullComponent', () => {
  let component: ProjectFullComponent;
  let fixture: ComponentFixture<ProjectFullComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjectFullComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectFullComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
