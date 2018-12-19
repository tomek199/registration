import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {User} from "./user/User";
import {UserService} from "./user/user.service";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  username = new FormControl('', [
    Validators.required,
    Validators.pattern('^[a-zA-Z0-9]{5,}$')
  ]);

  password = new FormControl('',[
    Validators.required,
    Validators.pattern('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$')
  ]);

  constructor(private userService: UserService,
              private alert: MatSnackBar) { }

  public getUsernameErrorMessage(): string {
    if (this.username.hasError('required'))
      return 'Username can\'t be empty';
    else if (this.username.hasError('pattern'))
      return 'Username should has at least five alphanumeric characters'
  }

  public getPasswordErrorMessage(): string {
    if (this.password.hasError('required'))
      return 'Password can\'t be empty';
    else if (this.password.hasError('pattern'))
      return `Password should has at least eight characters 
        and contains 1 number, 1 lowercase and 1 uppercase character`
  }

  public create(): void {
    let user = new User(this.username.value, this.password.value);
    this.userService.create(user).then(user => {
      this.alert.open(`User ${user.username} successfully created`);
    }, error => {
      this.alert.open('User with this username already exists! Please change the username.')
    });
  }

  public isFormInvalid(): boolean {
    return this.username.invalid || this.password.invalid;
  }

  public resetForm(): void {
    this.username.setValue(undefined);
    this.password.setValue(undefined);
    this.username.reset();
    this.password.reset();
    this.alert.dismiss();
  }
}
