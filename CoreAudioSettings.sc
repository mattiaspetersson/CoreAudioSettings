CoreAudioSettings {
	*prGetSettings {|dev|
		var data;
		var outputIndex, inputIndex, inputResult, outputResult, offset;

		data = "system_profiler SPAudioDataType".unixCmdGetStdOut;
		data = data.split(Char.nl);
		data.do{|x, i|
			if(x.contains("Default Input Device: Yes"), {inputIndex = i-2});
			if(x.contains("Default Output Device: Yes"), {outputIndex = i-2});
		};
		if(outputIndex-inputIndex == 1, {offset = 1}, {offset = 0});
		inputResult = data[inputIndex].copyRange(8, data[inputIndex].size);
		outputResult = data[outputIndex-offset].copyRange(8, data[outputIndex-offset].size);
		inputResult.pop;
		outputResult.pop;
		^[inputResult, outputResult][dev];
	}

	*inputDevice {
		^this.prGetSettings(0);
	}

	*outputDevice {
		^this.prGetSettings(1);
	}
}