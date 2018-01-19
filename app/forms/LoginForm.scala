package forms

import play.api.data._
import play.api.data.Forms._
import models.Login
object LoginForm {

  val form = Form(
    mapping(
      "email" -> email,
      "password" -> text
    )(Login.apply)(Login.unapply)
  )
}
