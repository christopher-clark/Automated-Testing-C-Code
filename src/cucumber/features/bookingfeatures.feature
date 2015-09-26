Feature: Code to automated a hotel booking

  Scenario: Automated hotel booking
    Given I have started the browser
	When I have performed a CitySearch
	When I have set a CheckInDate
	When I have set a setCheckOutDate
	When I have setGuests
	When I have selectedDistrict
	When I have selected the FirstHotelOnList
	When I have booked a Room
	When I enterDetails
	Then I can confirmReservation