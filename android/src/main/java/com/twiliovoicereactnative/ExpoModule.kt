package com.twiliovoicereactnative

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class ExpoModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("TwilioVoiceExpo")
  }
}